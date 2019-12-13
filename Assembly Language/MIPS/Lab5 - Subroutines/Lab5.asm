#------------------------------------------------------------------------
# Created by:  Fruitwala, Ulfat
#              ufruitwa
#              03 December 2019 
#
# Assignment:  Lab 5: Subroutines
#              CSE 12, Computer Systems and Assembly Language
#              UC Santa Cruz, Fall 2019
# 
# Description: Library of subroutines used to convert an array of
#              numerical ASCII strings to ints, sort them, and print
#              them.
# 
# Notes:       This file is intended to be run from the Lab 5 test file.
#------------------------------------------------------------------------

.text

j  exit_program                # prevents this file from running
                               # independently (do not remove)

#------------------------------------------------------------------------
# MACROS
#------------------------------------------------------------------------

#------------------------------------------------------------------------
# print new line macro

.macro lab5_print_new_line
    addiu $v0 $zero   11
    addiu $a0 $zero   0xA
    syscall
.end_macro

#------------------------------------------------------------------------
# print string

.macro lab5_print_string(%str)

    .data
    string: .asciiz %str

    .text
    li  $v0 4
    la  $a0 string
    syscall
    
.end_macro

#------------------------------------------------------------------------
# add additional macros here
#------------------------------------------------------------------------
# main_function_lab5_19q4_fa_ce12:
#
# Calls print_str_array, str_to_int_array, sort_array,
# print_decimal_array.
#
# You may assume that the array of string pointers is terminated by a
# 32-bit zero value. You may also assume that the integer array is large
# enough to hold each converted integer value and is terminated by a
# 32-bit zero value
# 
# arguments:  $a0 - pointer to integer array
#
#             $a1 - double pointer to string array (pointer to array of
#                   addresses that point to the first characters of each
#                   string in array)
#
# returns:    $v0 - minimum element in array (32-bit int)
#             $v1 - maximum element in array (32-bit int)
#-----------------------------------------------------------------------
# REGISTER USE
# $s0 - pointer to int array
# $s1 - double pointer to string array
# $s2 - length of array
#-----------------------------------------------------------------------

.text
main_function_lab5_19q4_fa_ce12: nop
    
    subi  $sp    $sp   16       # decrement stack pointer
    sw    $ra 12($sp)           # push return address to stack
    
    sw    $s0  8($sp)           # push save registers to stack
    sw    $s1  4($sp)
    sw    $s2   ($sp)
    
    move  $s0    $a0            # save ptr to int array		$s0 = pointer to int array
    move  $s1    $a1            # save ptr to string array		$s1 = pointer to string array
    
    move  $a0    $s1            # load subroutine arguments
    jal   get_array_length      # determine length of array
    move  $s2    $v0            # save array length			$s2 = array length
    
                                # print input header
                                 
    lab5_print_string("\n----------------------------------------")
    lab5_print_string("\nInput string array\n")
                       
    
    move	$a0	$s2		  # $a0 = array length
    move	$a1	$s1		  # $a1 = pointer to string array
    jal   	print_str_array      # print array of ASCII strings
    
    
    move	$a0   $s2		  # $a0 = array length
    move	$a1	$s1		  # $a1 = pointer to string array
    move	$a2	$s0		  # $a2 = pointer to integer array
    jal   	str_to_int_array    # convert string array to int array
                                
    
    move	$a1	$s0		  # $a1 = pointer to integer array                            
    jal   	sort_array          # sort int array
    # save min and max values from array
    

                                # print output header    
    lab5_print_new_line
    lab5_print_string("\n----------------------------------------")
    lab5_print_string("\nSorted integer array\n")
    
    
    move	$a1	$s0               # load subroutine arguments
    jal   	print_decimal_array   	# print integer array as decimal
                                	# save output values
    
    lab5_print_new_line
    
    
    lw	$v0	($s0)             # move min and max values from array
    lw	$v1	8($s0)            # to output registers
                                
            
    lw    $ra 12($sp)           # pop return address from stack
    lw    $s0  8($sp)           # pop save registers from stack
    lw    $s1  4($sp)
    lw    $s2   ($sp)
    addi  $sp    $sp   16       # increment stack pointer
    
    jr    $ra                   # return from subroutine

#-----------------------------------------------------------------------
# print_str_array	
#
# Prints array of ASCII inputs to screen.
#
# arguments:  $a0 - array length (optional)
# 
#             $a1 - double pointer to string array (pointer to array of
#                   addresses that point to the first characters of each
#                   string in array)
#
# returns:    n/a
#-----------------------------------------------------------------------
# REGISTER USE
# 		  $a0 - argument to print
#		  $v0 - print character/string
#-----------------------------------------------------------------------

.text
print_str_array: nop
    	
    	move	$t0,	$a1
    	
    	li 	$v0, 	4		  # $v0: print program argument
	lw 	$a0, 	($t0)		  # load first argument into $a0
	syscall
	
	li 	$a0, 	32		  # $a0: load the character of 'space'
	li 	$v0, 	11		  # $v0: printing space between arguments
	syscall
	
	li 	$v0, 	4		  # $v0: print program argument
	lw 	$a0, 	4($t0)	  # load second argument into $a0
	syscall
	
	li 	$a0, 	32		  # $a0: load the character of 'space'
	li 	$v0, 	11		  # $v0: printing space between arguments
	syscall
	
	li 	$v0, 	4		  # $v0: print program argument
	lw 	$a0, 	8($t0)	  # load third argument into $a0
	syscall                            

    jr    $ra
    
#-----------------------------------------------------------------------
# str_to_int_array
#
# Converts array of ASCII strings to array of integers in same order as
# input array. Strings will be in the following format: '0xABCDEF00'
# 
# i.e zero, lowercase x, followed by 8 hexadecimal digits, with A - F
# capitalized
# 
# arguments:  $a0 - array length (optional)
#
#             $a1 - double pointer to string array (pointer to array of
#                   addresses that point to the first characters of each
#                   string in array)
#
#             $a2 - pointer to integer array
#
# returns:    n/a
#-----------------------------------------------------------------------
# REGISTER USE
# $t0 - loop counter
# $t1 - array length
# $t2 - pointer to string array
# $t3 - pointer to integer array
#-----------------------------------------------------------------------

.text
str_to_int_array: nop
    
    subi	$sp	$sp	4		# decrement stack pointer
    sw	$ra	($sp)			# push return address on stack
    
    
#---store start--------------------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------
    
    
    move	$t2	$a1			# set $t2 = pointer to string array
    move	$t3	$a2			# set $t3 = pointer to integer array
    
    lw	$a0	($t2)			# load string argument in $a0
    jal	str_to_int			# str_to_int subroutine
    sw	$v0	($t3)			# store return value in integer array	
    
    move	$t2	$a1			# set $t2 = pointer to string array
    move	$t3	$a2			# set $t3 = pointer to integer array
    
    lw	$a0	4($t2)		# load string argument in $a0
    jal	str_to_int			# str_to_int subroutine
    sw	$v0	4($t3)		# store return value in integer array
    
    move	$t2	$a1			# set $t2 = pointer to string array
    move	$t3	$a2			# set $t3 = pointer to integer array
    
    lw	$a0	8($t2)		# load string argument in $a0
    jal	str_to_int			# str_to_int subroutine
    sw	$v0	8($t3)		# store return value in integer array
    
#---------------------------------------------------------------------------------------------------
#---store end----------------------------------------------------------------------------------------
    
    lw	$ra	($sp)			# load $ra with its original value
    addi	$sp	$sp	4		# increment stack pointer
    						
    jr   $ra

#-----------------------------------------------------------------------
# str_to_int	
#
# Converts ASCII string to integer. Strings will be in the following
# format: '0xABCDEF00'
# 
# i.e zero, lowercase x, followed by 8 hexadecimal digits, capitalizing
# A - F.
# 
# argument:   $a0 - pointer to first character of ASCII string
#
# returns:    $v0 - integer conversion of input string
#-----------------------------------------------------------------------
# REGISTER USE
#		  $s0 - append integer on it
# 		  $t0 - pointer to first character of ASCII string
#		  $t1 - contains a single loaded byte from $t0
#		  $t2 - loop counter
#-----------------------------------------------------------------------

.text
str_to_int: nop

	subi	$sp	$sp	4		# decrement stack pointer
    	sw	$s0	($sp)			# push $s0 on stack
	
	li	$s0	0			# initialize $s0 to 0
	
	addiu	$t0	$a0	0		# move contents of $a0 to $t0
	addiu	$t2	$zero	0		# load counter to 0
	
	loop_start:		bgt 		$t2		7	end_loop		# if $t2 > 7, branch to end of loop
				lb		$t1		2($t0)			# load a single byte into $t1
				
				bgt		$t1		57	letter		# if $t1  > 57, branch to letter
						addiu		$t1	$t1	-48		# subtract 48 from $t1 to result its integer
						j append					# jump to appending
					
				letter:	addiu		$t1	$t1	-55		# subtract 55 from $t1 to result its integer
	
				append:	sll		$s0	$s0	4		# shift left by half byte
				 		add		$s0	$s0	$t1		# add $t1 to $s0
				
				addiu		$t2		$t2	1			# increment loop counter
				addiu		$t0		$t0	1			# increment byte
				
				j		loop_start					# jump to beginning of loop
	end_loop:
	
	
	move 	$v0	$s0									# move the final integer into $v0
	
    	lw	$s0	($sp)			# load $s0 with its original value
    	addi	$sp	$sp	4		# increment stack pointer
    	
    jr   $ra										# go to return address
    
#-----------------------------------------------------------------------
# sort_array
#
# Sorts an array of integers in ascending numerical order, with the
# minimum value at the lowest memory address. Assume integers are in
# 32-bit two's complement notation.
#
# arguments:  $a0 - array length (optional)
#             $a1 - pointer to first element of array
#
# returns:    $v0 - minimum element in array
#             $v1 - maximum element in array
#-----------------------------------------------------------------------
# REGISTER USE
# $t0 - store 1st element
# $t1 - store 2nd element
# $t2 - store 3rd element
# $t3 - temp register used while swapping elements
#-----------------------------------------------------------------------

.text
sort_array: nop
	
	lw	$t0	($a1)		# $t0 = first element
	lw	$t1	4($a1)	# $t1 = second element
	lw	$t2	8($a1)	# $t2 = third element
	li	$t3	0		# temp register
	
	compare1: 
	bgt	$t0	$t1	swap1	# if $t0 > $t1, go to swap1
	j	compare2		# else go to compare2
	
	swap1:
	move	$t3	$t0		# $t3 = $t0
	move	$t0	$t1		# $t0 = $t1
	move	$t1	$t3		# $t1 = $t3
	
	compare2:
	bgt	$t1	$t2	swap2	# if $t1 > $t2, go to swap2
	j	compare3		# else go to compare3
	
	swap2:
	move	$t3	$t1		# $t3 = $t1
	move	$t1	$t2		# $t1 = $t2
	move	$t2	$t3		# $t2 = $t3	
	
	compare3:
	bgt	$t0	$t1	swap3	# if $t0 > $t1, go to swap3
	j	end_compare		# else go to end_compare
	
	swap3:
	move	$t3	$t0		# $t3 = $t0
	move	$t0	$t1		# $t0 = $t1
	move	$t1	$t3		# $t1 = $t3
	
	end_compare:
	
	sw	$t0	($a1)		# store elements in integer array
	sw	$t1	4($a1)
	sw	$t2	8($a1)
	
	move	$v0	$t0		# set $v0 to minimum element
	move	$v1	$t2		# set $v1 to maximum element
	
    jr   $ra

#-----------------------------------------------------------------------
# print_decimal_array
#
# Prints integer input array in decimal, with spaces in between each
# element.
#
# arguments:  $a0 - array length (optional)
#             $a1 - pointer to first element of array
#
# returns:    n/a
#-----------------------------------------------------------------------
# REGISTER USE
# $t0 - loop counter
# $a0 - subroutine argument
# $v0 - syscall 11
#-----------------------------------------------------------------------

.text
print_decimal_array: nop

	subi	$sp	$sp	4			# decrement stack pointer
    	sw	$ra	($sp)				# push return address on stack
	
	lw	$a0	($a1)				# load $a0 with argument
	jal 	print_decimal			# go into subroutine
	
	li	$a0	0x20				# load $a0 with 'space'
	li	$v0	11				# print character
	syscall
	
	lw	$a0	4($a1)				# load $a0 with argument
	jal 	print_decimal			# go into subroutine
	
	li	$a0	0x20				# load $a0 with 'space'
	li	$v0	11				# print character
	syscall
	
	lw	$a0	8($a1)				# load $a0 with argument
	jal 	print_decimal			# go into subroutine
	
	li	$a0	0x20				# load $a0 with 'space'
	li	$v0	11				# print character
	syscall
	
	lw	$ra	($sp)				# load $ra with its original value
    	addi	$sp	$sp	4			# increment stack pointer
	
    	jr   	$ra
    
#-----------------------------------------------------------------------
# print_decimal
#
# Prints integer in decimal representation.
#
# arguments:  $a0 - integer to print
#
# returns:    n/a
#-----------------------------------------------------------------------
# REGISTER USE
# $t0 - integer value
# $t1 - flag for negative integer
# $t2 - the base used for dividing (10)
# $t3 - remainder
# $t4 - counter for stack
# $v0 - print character syscall
#-----------------------------------------------------------------------

.text
print_decimal: nop
	move	$t0	$a0			# load $t0 with $a0
	li	$t1	0			# set $t1 = 0
	li	$t2	10			# set $t2 = 10
	li	$t4	0			# set $t4 = 0
	
	bgt	$t0	$zero	convert	# if $t0 > 0, go to convert
	li	$t1	1			# else $t0 is negative so set $t1 to 1
	nor	$t0	$t0	$zero		# invert the bits of $t0 since it's a 2sc
	addi	$t0	$t0	1		# add 1 to result in a positive integer	
	
	convert:
	div 	$t0	$t2			# integer/10
	mfhi	$t3				# $t3 = remainder
	mflo	$t0				# $t0 = quotient, also the next thing to divide
	
	subi	$sp	$sp	4		# decrement stack pointer
	sw	$t3	($sp)			# store remainder on the stack
	addi	$t4	$t4	1		# increment $t4

	bgtz 	$t0	convert		# if $t0 > 0, loop back to convert
	
	#Print the negative sign
	beq	$t1	0	print		# if $t1 = 0, branch to print
	li	$a0	0x2d			# else load $a0 with '-'
	li	$v0	11			# print character
	syscall
	
	#Print each digit
	print:
	lw	$t0	($sp)			# load $t0 with first element on stack
	addi	$sp	$sp	4		# increment the stack
	subi	$t4	$t4	1		# decrement counter
	
	addi	$t0	$t0	48		# convert integer into ascii character
	move	$a0	$t0			# set $a0 to $t0
	li	$v0	11			# print character
	syscall
	bgt	$t4	0	print		# if $t4 > 0, branch back to print
	
    	jr   	$ra

#-----------------------------------------------------------------------
# exit_program (given)
#
# Exits program.
#
# arguments:  n/a
#
# returns:    n/a
#-----------------------------------------------------------------------
# REGISTER USE
# $v0: syscall
#-----------------------------------------------------------------------

.text
exit_program: nop
    
    addiu   $v0  $zero  10      # exit program cleanly
    syscall
    
#-----------------------------------------------------------------------
# OPTIONAL SUBROUTINES
#-----------------------------------------------------------------------
# You are permitted to delete these comments.

#-----------------------------------------------------------------------
# get_array_length (optional)
# 
# Determines number of elements in array.
#
# argument:   $a0 - double pointer to string array (pointer to array of
#                   addresses that point to the first characters of each
#                   string in array)
#
# returns:    $v0 - array length
#-----------------------------------------------------------------------
# REGISTER USE
# 
#-----------------------------------------------------------------------

.text
get_array_length: nop
    
    addiu   $v0  $zero  3       # replace with /code to
                                # determine array length
    jr      $ra
    
#-----------------------------------------------------------------------
# save_to_int_array (optional)
# 
# Saves a 32-bit value to a specific index in an integer array
#
# argument:   $a0 - value to save
#             $a1 - address of int array
#             $a2 - index to save to
#
# returns:    n/a
#-----------------------------------------------------------------------
# REGISTER USE
# 
#-----------------------------------------------------------------------
