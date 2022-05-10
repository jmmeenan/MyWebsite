#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main (int nargs, char* args[]) {
    if(nargs <2){
        printf("You did not add a command\n");
        exit(1);
    }
    char* input = args[1];
    printf("Running program %s\n", input);
    char cmd[100] = "gcc ";
    strcat(cmd, input);
    strcat(cmd, " -o run1");
    printf("%s\n", cmd);
    system(cmd);
    char cmd2[100] = "./run1";
    //TODO If the excutable needs some input we need to do basiclly the same thing as what we did above
    if(nargs >2){//Not tested
        for(int i =2; i<nargs; i++){
            char* input2 = args[i];
            strcat(cmd2, input2);
        }
    }
    system(cmd2);


    exit(EXIT_SUCCESS);
}