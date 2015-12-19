REM This will create a copy 
echo Did you remember to set the mother pom back?
Pause

cd..
rmdir simple-archetype2 /S /Q
md simple-archetype2
xcopy simple-archetype simple-archetype2 /E /F
cd simple-archetype2
rm .project
rmdir .settings  /S /Q
rmdir target  /S /Q

rm application\.project
rm application\.classpath
rmdir application\.settings /S /Q
rmdir application\target /S /Q

rm create.bat

call mvn archetype:create-from-project
cd target\generated-sources\archetype
ren src\main\resources\archetype-resources\application\src\main\java\simple-archetype __parentArtifactId__
ren src\main\resources\archetype-resources\application\src\itest\java\simple-archetype __parentArtifactId__
ren src\main\resources\archetype-resources\application\src\test\java\simple-archetype __parentArtifactId__

start .
rmdir target /S /Q
findstr /S simple-archetype *.*

echo remember to set the pom accordingly
echo remeber to do mvn archetype:create-from-project install afterwards