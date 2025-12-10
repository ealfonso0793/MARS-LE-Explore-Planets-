.data
Label: .ascii "ROVERLANDING"
.text


# Load memory
Establish $t1, 0
# sets register to 0 and display a special message in console
JUPITER $t0 
# Returns 3 (Absolute value of (0 - 3))
Away $t0
# stores value $t0 into memory address at $t1
Record $t0, Label($t1)
Inject $t2, Label($t1)
