# CrackShotPlus-1.20.1
Guide of how to modify CSP to working on 1.20.1. This repo does not redistribute CSP.

# Setup
1. A 1.20.1 Spigot Plugin WorkSpace
2. Crack Shot & Crack Shot Plus
3. A Bytecode Editor like Recaf
4. Make CS&CSP jar as a dep lib in your workspace
5. Copy the me/ folder into your workspace's source folder
6. Run build
7. Copy compiled me/DeeCaaD/CrackShotPlus/Packets/SetupVersion_1_20_R1.class to CSP's Packet package
8. Open your new CSP jar with Bytecode Editor
9. Copy setupVer() method's bytecode in compiled API.class to new CSP jar's setupVer() method's bytecode
10. Put the new CSP jar in your 1.20.1 server's plugins
11. It should working on 1.20.1 Mohist and most Spigot forks
12. If it's not working you have to figure by youself, this guide is provided with "AS IS"

PS: If you wanna use it on 1.19.4 server, remap package org.bukkit.craftbukkit.v1_19_R2 to org.bukkit.craftbukkit.v1_19_R3 in SetupVersion_1_19_R2.class
