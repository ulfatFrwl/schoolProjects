################################################################################
# Created by:  Fruitwala, Ulfat
#              ufruitwa
#              4 November 2019
#
# Assignment:  Lab 3: ASCII Forest
#              CSE 012, Computer Systems and Assembly Language
#              UC Santa Cruz, Fall 2019
# 
# Description: This program prompts user for two integers, x and y, then prints
# 		   x trees with y length.
#
# Notes:       This program is intended to be run from the MARS IDE.
################################################################################
#
####pseudo code
# .text
#
# do:	ask for user inputs x (number of trees) and y (size of trees)
# while: (x < 0) and (y < 1)
# 
# for ( i = x ; i > 1; i--) 
#	print /\
#
# for ( i = y-1 ; i > 1; i--)
#	for (j = x; j > 1; j--)
#		print /  \
#
# for ( i = x ; i > 1; i--)
#	print ----
#
# for ( i = y-1 ; i > 1; i--)
#	for (j = x; j > 1; j--)
#		print ||
#
# exit program
#
# .data
#
# prompts for user input


.text
	
	# First do while loop for prompting number of trees
	
	do1:		li 	$v0, 4			# $v0: load service 4 to print a string
			la 	$a0, prompt1		# prompt user for number of trees
			syscall
	
			
			li 	$v0, 5			# $v0: load service 5 to read user input (integer)
			syscall				#	$v0 contains the number of trees
	
			while1:				# test if input is greater than 0
			bgt 	$v0, $zero, confirmX	# if greater than 0, branch to confirmX statement	
											
									
			li 	$v0, 4			# $v0: load service 4 to print a string	
			la 	$a0, invalidText		# $a0: load string "Invalid entry!" and print it on screen
			syscall 
	
			j do1					# jump to beginning of do1 to repeat process until user input is greater than 0
	
			confirmX:	
			move 	$s0, $v0			# copy value from $v0 (user input) and move it to $s0 
	

	
	# Second do while loop for prompting size of trees
	
	do2:		li 	$v0, 4			# $v0: load service 4 to print a string
			la 	$a0, prompt2		# a0: prompt user for size of trees
			syscall
	
			li 	$v0, 5			# $v0: load service 5 to read user input (integer)	
			syscall				#	 $v0 contains the size of trees
			
			while2:				# test if input is greater than 1
			bgt 	$v0, 1, confirmY		# if greater than 1, branch to confirmY statement
									
			li 	$v0, 4			# $v0: load service 4 to print a string 	
			la 	$a0, invalidText		# $a0: load string "Invalid entry!" and print it on screen
			syscall 
	
			j do2					# jump to beginning of do2 to repeat process until user input is greater than 1
	
			confirmY:
			move 	$s1, $v0			# copy value from $v0 (user input) and move it to $s1
	
	
	
			move 	$t0, $s0 			# copy value from $s0 (number of trees) and move it to $t0
			
	# for loop that prints tree tops
	start_loop1:
		
			BLT 	$t0, 1, end_loop1		# branch to end_loop1 when $t0 < 1
			
			
			###### code block start	####### $a0: load characters, $v0: load service 11
			   						
			#space
			li 	$a0, 32
			li 	$v0, 11
			syscall
			   
			#/
			li 	$a0, 47
			li 	$v0, 11
			syscall
			   
			#\
			li 	$a0, 92
			li 	$v0, 11
			syscall
			   
			#space
			li 	$a0, 32
			li 	$v0, 11
			syscall
			   
			#space
			li 	$a0, 32
			li 	$v0, 11
			syscall
			   
			#space
			li 	$a0, 32
			li 	$v0, 11
			syscall
			   
			###### code block end ######
			
			sub 	$t0, $t0, 1			# $t0 = $t0 - 1 (decrement $t0)
			
			j 	start_loop1			# jump to beginning of loop
		
	end_loop1: 						# end of loop
	
	
			move 	$t0, $s1 			# copy value in $s1 (size of trees) and move it to $t0
			sub 	$t0, $t0, 1 		# $t0 = $t0 - 1 (only need to print the greeneries bottom of tree tops)
	
	#nested for loop that prints the greeneries of x number of trees with size y

	# outer loop (y-1)
	start_loop2:

			#new line for next row of greeneries
			li 	$v0, 4			# $v0: load service 4 to print a string
			la 	$a0, newline		# $a0: print a newline
			syscall
			
			BLT 	$t0, 1, end_loop2		# branch to end_loop2 when $t0 < 1
			
			
			###### OUTER LOOP code block start #######
			
			move 	$t1, $s0 			# copy value from $s0 (number of trees) and move it to $t1
			
			#  inner loop (x)
			start_loop3:
				
				BLT 	$t1, 1, end_loop3	# branch to end_loop3 when $t1 < 1
				
				###### INNER LOOP code block start ####### 	$a0: load characters, $v0: load service 11
				
				#/
			   	li 	$a0, 47
			   	li 	$v0, 11
			   	syscall
			   	
			   	#space
			   	li 	$a0, 32
			   	li 	$v0, 11
			   	syscall
			   	
			   	#space
			   	li 	$a0, 32
			   	li 	$v0, 11
			   	syscall
			   	
			   	#\
			   	li 	$a0, 92
			   	li 	$v0, 11
			   	syscall
			   	
			   	#space
			   	li 	$a0, 32
			   	li 	$v0, 11
			   	syscall
			  	
			  	#space
			   	li 	$a0, 32
			   	li 	$v0, 11
			   	syscall
				
				###### INNER LOOP code block end  #####
	
				sub 	$t1, $t1, 1		# $t1 = $t1 - 1 (decrement $t1)
				
				j 	start_loop3		# jump to beginning of inner loop
				
			end_loop3:				# end of inner loop
			
			
			###### OUTER LOOP code block end ###### 
			
			sub 	$t0, $t0, 1			# $t0 = $t0 - 1 (decrement $t0)
			
			j 	start_loop2			# jump to beginning of outer loop
		
	end_loop2:						# end of outer loop
			
			
			move 	$t0, $s0 			# copy value from $s0 (number of trees) and move it to $t0
			
	# for loop that prints the width of trees
	start_loop4:
		
			BLT 	$t0, 1, end_loop4		# branch to end_loop4 when $t0 < 1
			
			###### code block start	###### $a0: load characters, $v0: load service 11
			   
			#-
			li 	$a0, 45
			li 	$v0, 11
			syscall
			   
			#-
			li 	$a0, 45
			li 	$v0, 11
			syscall
			   
			#-
			li 	$a0, 45
			li 	$v0, 11
			syscall
			   
			#-
			li 	$a0, 45
			li 	$v0, 11
			syscall
			   
			#space
			li 	$a0, 32
			li 	$v0, 11
			syscall
			   
			#space
			li 	$a0, 32
			li 	$v0, 11
			syscall
			   
			###### code block end ######
			
			sub 	$t0, $t0, 1			# $t0 = $t0 - 1 (decrement $t0)
			
			j 	start_loop4			# jump to beginning of loop
		
	end_loop4:						# end of loop	
				
				
			move 	$t0, $s1 			# copy value from $s1 (size of trees) and move it to $t0
			li 	$t1, 2			# set $t1 to be 2
			div 	$t0, $t1			# divide $t0/$t1 (size of trees/2)
			mflo 	$t0	 			# register lo contains the quotient of the division so move the quotient from register lo to $t0

	#nested for loop that prints the trunks of x number of trees with size y

	# outer loop (y)
	start_loop5:
		
			#new line for each trunk
			li 	$v0, 4			# $v0: load service 4 to print a string 
			la 	$a0, newline		# $a0: print a newline
			syscall
			
			BLT 	$t0, 1, end_loop5		# branch to end_loop5 when $t0 < 1
			
			###### OUTER LOOP code block start ###### 
			
			move 	$t1, $s0 			# copy value from $s0(number of trees) and move it to $t1 
			
			# inner loop (x)
			start_loop6:
				
				BLT 	$t1, 1, end_loop6	# branch to end_loop6 when $t1 < 1
				
				###### INNER LOOP code block start ###### $a0: load characters, $v0: load service 11
				
				#space
			   	li 	$a0, 32
			   	li 	$v0, 11
			   	syscall
			   	
				#|
			   	li 	$a0, 124
			   	li 	$v0, 11
			   	syscall
			   	
			   	#|
			   	li 	$a0, 124
			   	li 	$v0, 11
			   	syscall
			   	
			   	#space
			   	li 	$a0, 32
			   	li 	$v0, 11
			   	syscall
			   	
			   	#space
			   	li 	$a0, 32
			   	li 	$v0, 11
			   	syscall
			   	
			   	#space
			   	li 	$a0, 32
			   	li 	$v0, 11
			   	syscall
			   	
				###### code block end ###### 
	
				sub 	$t1, $t1, 1		# $t1 = $t1 - 1 (decrement $t1)
				
				j 	start_loop6		# jump to beginning of inner loop
				
			end_loop6:				# end of inner loop
			
			###### code block end ######
			
			sub 	$t0, $t0, 1			# $t0 = $t0 - 1 (decrement $t0)
			
			j 	start_loop5			# jump to beginning of outer loop
		
	end_loop5:						# end of outer loop		
			


	#Exit program
	li $v0, 10						# $v0: load service 10
	syscall						

	
	
.data
	prompt1: .asciiz "Enter the number of trees to print (must be greater than 0): "
	prompt2: .asciiz "Enter the size of one tree (must be greater than 1): "
	newline: .asciiz "\n"
	invalidText: .asciiz "Invalid entry!\n"
