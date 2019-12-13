###########################################################################################################
# Created by:  Fruitwala, Ulfat
#              ufruitwa
#              12 November 2019
#
# Assignment:  Lab 4: Sorting Floats
#              CSE 012, Computer Systems and Assembly Language
#              UC Santa Cruz, Fall 2019
# 
# Description: This program prompts user for three arguments in IEEE 754 single precision floating point
#		   format then prints them to the screen in order in floating point format and decimal format.
#
# Notes:       This program is intended to be run from the MARS IDE.
###########################################################################################################

#pseudocode
#
# $a0 = number of arguments
# ($a1) = address of first character of argument 1
# 4($a1) = address of first character of argument 2
# 8($a1) = address of first character of argument 3
#
#
# .text
#
# Print arguements
#
# print arg 1
# 	use syscall 4	
# 	load $a0 with ($a1)
# 	syscall
#
# print arg 2
# 	use syscall 4	
# 	load $a0 with 4($a1)
# 	syscall
#
# print arg 3
# 	use syscall 4	
# 	load $a0 with 8($a1)
# 	syscall
#
# Convert ascii to integer equivalent
#
# outerloop iterate 3 times
#
#		inner loop iterate 7 times
#			load byte $t0
#			shift left logical $s0 by 4 bytes
#			add $s0 = $s0 + $t1
#		
#		if outer loop is in first iteration
#			$s1 = $s0
#		if outer loop is in first iteration
#			$s2 = $s0
#		if outer loop is in first iteration
#			$s3 = $s0
#
#
# Convert IEEE to decimal
#	mtc1 $s1, $s2, $s3 to $f0, $f1, $f2
#
# Compare the decimal values
#	c.lt.s $f0, $f1 / $f1, $f2 / $f0, $f1  (3 compares for 3 values)
#	bc1f	(branch to swap labels if flag is false)
#	
#	compare1/2/3
#	exchange $f0, $f1 / $f1, $f2 / $f0, $f1
#
# Print sorted IEEE 
#	mfc1 $f0, $f1, $f2 to $a0
#	use syscall 34 to print
#
#Print sorted Decimal
#
#	mov.s from $f0, $f1, $f2 to $f12
#	use syscall 2 to print
#
#
#end of program (use syscall 10 to end)
#
#


.text

print_arguments:

	li 	$v0, 	4											# $v0: syscall 4, print string
	la 	$a0, 	prompt1										# $a0: print "Program arguments: "
	syscall

	li 	$v0, 	4											# $v0: print program argument
	lw 	$a0, 	($a1)											# load first argument into $a0
	syscall
	
	li 	$a0, 	32											# $a0: load the character of 'space'
	li 	$v0, 	11											# $v0: printing space between arguments
	syscall
	
	li 	$v0, 	4											# $v0: print program argument
	lw 	$a0, 	4($a1)										# load second argument into $a0
	syscall
	
	li 	$a0, 	32											# $a0: load the character of 'space'
	li 	$v0, 	11											# $v0: printing space between arguments
	syscall
	
	li 	$v0, 	4											# $v0: print program argument
	lw 	$a0, 	8($a1)										# load third argument into $a0
	syscall
	
	li	$v0	4											# $v0: print string
	la	$a0	newline										# $a0: print a newline, following lab output
	syscall
	syscall	
	
	
Convert_IEEE:												# Ascii code gets converted to its integer rep

	initializations1:
	addiu $t2, $zero, 0										# $t2: outerloop iterator, initialize it to 0
	li 	$t4, 0x39											# initialize $t4 to 57; comparator 
	addiu $s0, $s0, 	0										# initialize $s0 to 0. will contain first converted arg
	addiu $s1, $s1, 	0										# initialize $s1 to 0. will contain second converted arg
	addiu $s2, $s2, 	0										# initialize $s2 to 0. will contain third converted arg
	
	
	outerloop_start: 	bgt	$t2,	2,	outerloop_end					#branch to outerloop_end if $t2 > 2
				
				initializations2:
				lw	$t0,	($a1)								# load word from ($a1) to $t0
				addiu $t0,	$t0,	 2							# move 2 bytes forward to ignore 0x in the argument
				addiu $t3, 	$zero, 0							# $t3: innerloop iterator, initialize it to 0
				
	 			innerloop_start:	bgt 	$t3, 7, innerloop_end			# branch to innerloop_end if $t3 > 7
	 						
	 						lb	$t1, ($t0)					# load a byte from $t0 to $t1
	 						bgt	$t1, $t4, letter				# branch to letter if $t1 > 57
	 						addiu $t1, $t1, -48				# else subtract 48 from $t1
	 						j 	append					# jump to append
	 						
	 						letter: 
	 						addiu $t1, $t1, -55				# subtract 55 from $t1
	 						
	 						append:
	 						sll   $s0, $s0, 4					# shift $s0 left by 4 bits
							add 	$s0, $s0, $t1				# set $s0 = $s0 + $t1
							
							inner_increments:
							addiu $t0, $t0, 1					# increment $t0 by 1 to move to next byte
							addiu $t3, $t3, 1					# increment $t3 by 1 to iterate
							
							j 	innerloop_start				# jump to beginning of innerloop	
	 						
	 			innerloop_end:
	 			
	 			beq 	$t2, 	0, 	arg1							# if in first iteration, go to arg1
	 			beq	$t2,	1,	arg2							# if in second iteration, go to arg2
	 			beq	$t2,	2,	arg3							# if in third iteration, go to arg3
	 			
	 			arg1:	addiu	$s1,	$s0,	0						# set $s1 = $s0
	 				j outer_increments							# jump to increments
	 				
	 			arg2:	addiu $s2,	$s0,	0						# set $s2 = $s0
	 				j outer_increments							# jump to increments
	 				
	 			arg3:	addiu	$s3,	$s0,	0						# set $s3 = $s0
	 			
	 			outer_increments:
	 			addiu $a1, 	$a1, 	4							# increment to the next word (argument)
	 			addiu $t2,	$t2,  1							# increment $t2 by 1 to iterate
	 			
	 			j	outerloop_start							# jump to outerloop_start
	 			
	
	outerloop_end:
	
	
	
convert_Decimal:												# convert IEEE to decimal representation 

	mtc1 		$s1, 	$f0										# store first arg in $f0
	mtc1 		$s2, 	$f1										# store second arg in $f1
	mtc1 		$s3, 	$f2										# store third arg in $f2
	
	
compare_Decimal:												# compare the 3 decimal numbers
	
	compare1:
	c.lt.s 	$f0, 	$f1										# compare $f0 and $f1
	bc1f 		swap1											# if $f0 > $f1, go to swap1
	j 		compare2										# else go to compare2
		
	swap1:						
	mov.s 	$f3, 	$f0										# store $f0 in $f3
	mov.s 	$f0, 	$f1										# move $f1 to $f0
	mov.s 	$f1, 	$f3										# move $f3 to #f1
		
	compare2:
	c.lt.s 	$f1, 	$f2										# compare $f1 and $f2
	bc1f 		swap2											# if $f1 > $f2, go to swap2
	j 		compare3										# else go to compare3
	
	swap2:
	mov.s 	$f3, 	$f1										# store $f1 in $f3
	mov.s 	$f1, 	$f2										# move $f2 to $f1
	mov.s 	$f2, 	$f3										# move $f3 to $f2
	
	compare3:
	c.lt.s 	$f0, 	$f1										# compare $f0 and $f1 
	bc1f 		swap3											# if $f0 > $f1, go to swap3
	j 		end_of_compare									# else go to end_of_compare
	
	swap3:
	mov.s 	$f3, 	$f0										# store $f0 in $f3
	mov.s 	$f0, 	$f1										# move $f1 to $f0
	mov.s 	$f1, 	$f3										# move $f3 to #f1
	
	end_of_compare: 											# end of comparing 3 arguments
	
	
print_IEEE:	
	
	li 		$v0,	4										# $v0: print string
	la		$a0,	prompt2									# $a0: print prompt2
	syscall																							# print sorted IEEE values
	
	li		$v0, 	34										# $v0: print hex
	mfc1 		$a0,	$f0										# move $f0 to $a0
	syscall
	
	li 		$a0, 	32										# $a0: load the character of 'space'
	li 		$v0, 	11										# $v0: printing space between arguments
	syscall
	
	li		$v0, 	34										# $v0: print hex
	mfc1 		$a0,	$f1										# move $f1 to $a0
	syscall
	
	li 		$a0, 	32										# $a0: load the character of 'space'
	li 		$v0, 	11										# $v0: printing space between arguments
	syscall
	
	li		$v0, 	34										# $v0: print hex
	mfc1 		$a0,	$f2										# move $f2 to $a0
	syscall
	
	li		$v0	4										# $v0: print string
	la		$a0	newline									# $a0: print a newline, following lab output
	syscall
	syscall
	

print_Decimal:	

	li 		$v0,	4										# $v0: print string
	la		$a0,	prompt3									# $a0: print prompt3
	syscall
	
	li 		$v0, 	2										# $v0: print fp in decimal
	mov.s 	$f12, $f0										# set $f12 to $f0
	syscall
	
	li 		$a0, 	32										# $a0: load the character of 'space'
	li 		$v0, 	11										# $v0: printing space between arguments
	syscall
	
	li 		$v0, 	2										# $v0: print fp in decimal
	mov.s 	$f12, $f1										# set $f12 to $f1
	syscall
	
	li 		$a0, 	32										# $a0: load the character of 'space'
	li 		$v0, 	11										# $v0: printing space between arguments
	syscall
	
	li 		$v0, 	2										# $v0: print fp in decimal
	mov.s 	$f12, $f2										# set $f12 to $f2
	syscall
	
	li		$v0	4										# $v0: print string
	la		$a0	newline									# $a0: print a newline, following lab output
	syscall
	
	
end: 

li	$v0 	10												# ending program
syscall


.data

prompt1: .asciiz "Program arguments: \n"
prompt2: .asciiz "Sorted values (IEEE 754 single precision floating point format): \n"
prompt3: .asciiz "Sorted values (decimal): \n"
newline: .asciiz "\n"











