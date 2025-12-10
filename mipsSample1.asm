#initialize $t1 
Establish $t1, 4
#sets register to 0 and displays a special message
SUN $t0
# prevent the use of SUN
NOVA $t0
# when ran again, $t1 will remain 4 and a new message is printed in the console noting SUN’s deactivation
SUN $t1 
#Reenable SUN with rebirth
REBI $t0
# function now sets $t1 to 0
SUN $t1

