#!/bin/bash

if [[ $1 == "A" ]]
then
    java -cp bin Merge Project1/A/1.log outputs/A/merge_1.log
    java -cp bin Merge Project1/A/2.log outputs/A/merge_2.log
    java -cp bin Merge Project1/A/4.log outputs/A/merge_4.log
    java -cp bin Merge Project1/A/8.log outputs/A/merge_8.log
    java -cp bin Merge Project1/A/16.log outputs/A/merge_16.log
    java -cp bin Merge Project1/A/32.log outputs/A/merge_32.log
    java -cp bin Merge Project1/A/64.log outputs/A/merge_64.log
    java -cp bin Merge Project1/A/128.log outputs/A/merge_128.log
    java -cp bin Merge Project1/A/256.log outputs/A/merge_256.log
    java -cp bin Merge Project1/A/512.log outputs/A/merge_512.log
    java -cp bin Merge Project1/A/1024.log outputs/A/merge_1024.log
    java -cp bin Merge Project1/A/2048.log outputs/A/merge_2048.log
    java -cp bin Merge Project1/A/4096.log outputs/A/merge_4096.log
    java -cp bin Merge Project1/A/8192.log outputs/A/merge_8192.log
    java -cp bin Merge Project1/A/16384.log outputs/A/merge_16384.log
    java -cp bin Merge Project1/A/32768.log outputs/A/merge_32768.log
    java -cp bin Merge Project1/A/65536.log outputs/A/merge_65536.log
    java -cp bin Merge Project1/A/131072.log outputs/A/merge_131072.log
    java -cp bin Merge Project1/A/262144.log outputs/A/merge_262144.log
    java -cp bin Merge Project1/A/524288.log outputs/A/merge_524288.log
    java -cp bin Merge Project1/A/1048576.log outputs/A/merge_1048576.log
    java -cp bin Merge Project1/A/2097152.log outputs/A/merge_2097152.log
    java -cp bin Merge Project1/A/4194304.log outputs/A/merge_4194304.log
elif [[ $1 == "B" ]]
then
    java -cp bin Merge Project1/B/1.log outputs/B/merge_1.log
    java -cp bin Merge Project1/B/2.log outputs/B/merge_2.log
    java -cp bin Merge Project1/B/4.log outputs/B/merge_4.log
    java -cp bin Merge Project1/B/8.log outputs/B/merge_8.log
    java -cp bin Merge Project1/B/16.log outputs/B/merge_16.log
    java -cp bin Merge Project1/B/32.log outputs/B/merge_32.log
    java -cp bin Merge Project1/B/64.log outputs/B/merge_64.log
    java -cp bin Merge Project1/B/128.log outputs/B/merge_128.log
    java -cp bin Merge Project1/B/256.log outputs/B/merge_256.log
    java -cp bin Merge Project1/B/512.log outputs/B/merge_512.log
    java -cp bin Merge Project1/B/1024.log outputs/B/merge_1024.log
    java -cp bin Merge Project1/B/2048.log outputs/B/merge_2048.log
    java -cp bin Merge Project1/B/4096.log outputs/B/merge_4096.log
    java -cp bin Merge Project1/B/8192.log outputs/B/merge_8192.log
    java -cp bin Merge Project1/B/16384.log outputs/B/merge_16384.log
    java -cp bin Merge Project1/B/32768.log outputs/B/merge_32768.log
    java -cp bin Merge Project1/B/65536.log outputs/B/merge_65536.log
    java -cp bin Merge Project1/B/131072.log outputs/B/merge_131072.log
    java -cp bin Merge Project1/B/262144.log outputs/B/merge_262144.log
    java -cp bin Merge Project1/B/524288.log outputs/B/merge_524288.log
    java -cp bin Merge Project1/B/1048576.log outputs/B/merge_1048576.log
    java -cp bin Merge Project1/B/2097152.log outputs/B/merge_2097152.log
    java -cp bin Merge Project1/B/4194304.log outputs/B/merge_4194304.log
else
    if [[ $1 == "C" ]]
    then
        java -cp bin Merge Project1/C/1.log outputs/C/merge_1.log
        java -cp bin Merge Project1/C/2.log outputs/C/merge_2.log
        java -cp bin Merge Project1/C/4.log outputs/C/merge_4.log
        java -cp bin Merge Project1/C/8.log outputs/C/merge_8.log
        java -cp bin Merge Project1/C/16.log outputs/C/merge_16.log
        java -cp bin Merge Project1/C/32.log outputs/C/merge_32.log
        java -cp bin Merge Project1/C/64.log outputs/C/merge_64.log
        java -cp bin Merge Project1/C/128.log outputs/C/merge_128.log
        java -cp bin Merge Project1/C/256.log outputs/C/merge_256.log
        java -cp bin Merge Project1/C/512.log outputs/C/merge_512.log
        java -cp bin Merge Project1/C/1024.log outputs/C/merge_1024.log
        java -cp bin Merge Project1/C/2048.log outputs/C/merge_2048.log
        java -cp bin Merge Project1/C/4096.log outputs/C/merge_4096.log
        java -cp bin Merge Project1/C/8192.log outputs/C/merge_8192.log
        java -cp bin Merge Project1/C/16384.log outputs/C/merge_16384.log
        java -cp bin Merge Project1/C/32768.log outputs/C/merge_32768.log
        java -cp bin Merge Project1/C/65536.log outputs/C/merge_65536.log
        java -cp bin Merge Project1/C/131072.log outputs/C/merge_131072.log
        java -cp bin Merge Project1/C/262144.log outputs/C/merge_262144.log
        java -cp bin Merge Project1/C/524288.log outputs/C/merge_524288.log
        java -cp bin Merge Project1/C/1048576.log outputs/C/merge_1048576.log
        java -cp bin Merge Project1/C/2097152.log outputs/C/merge_2097152.log
        java -cp bin Merge Project1/C/4194304.log outputs/C/merge_4194304.log
    else
        echo "Invalid data set, try again"
    fi
fi
