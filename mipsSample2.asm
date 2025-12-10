# load registers
Establish $t0, 0
Establish $t1, 0
Establish $t2, 10
# create main loop body

Loop:
#adds 1 to $t0
Teleport $t1, $t1, 1
Travel $t0, $t0, $t1
# branches if $t1 != $t2
WarpCheck $t0, $t2, Loop
Warp End

End: