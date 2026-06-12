# for players editing, change the values in here to change the speed, trial and error! - DerexXD

execute as @e[type=minecraft:happy_ghast] at @s if entity @a[distance=..5] run attribute @s minecraft:flying_speed base set 0.1
execute as @e[type=minecraft:happy_ghast] at @s unless entity @a[distance=..5] run attribute @s minecraft:flying_speed base set 0.05

