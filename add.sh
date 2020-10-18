#!/usr/bin/env bash

task=$1

cp -r template $task
mv $task/src/T.java $task/src/$task.java
mv $task/src/TTest.java $task/src/${task}Test.java

sed -i '' -e "s/class T/class $task/g" $task/src/$task.java

sed -i '' -e "s/T\.class/$task\.class/g" $task/src/${task}Test.java
sed -i '' -e "s/class TTest/class ${task}Test/g" $task/src/${task}Test.java

sed -i '' -e "s/template/$task/g" $task/pom.xml

# Mac OS's sed has bag with backslash..
#sed -i "/<modules>/a <module>$task<module/>" pom.xml
# https://www.linux.org.ru/forum/admin/11562410?cid=11562430
# https://www.linuxquestions.org/questions/programming-9/sed-error-command-c-expects-%5C-followed-by-text-under-os-x-but-works-in-linux-730997/
sed -i '' "/<modules>/a\\
        <module>$task</module>
" pom.xml

# todo: Try rsync instead: https://stackoverflow.com/questions/4585929/how-to-use-cp-command-to-exclude-a-specific-directory
rm $task/template.iml
