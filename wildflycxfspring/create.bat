cd..
rmdir springcxf2 /S /Q
md springcxf2
xcopy springcxf springcxf2 /E /F
cd springcxf2
rm .project
rmdir .settings  /S /Q
rmdir target  /S /Q

rm applicationservice\.project
rm applicationservice\.classpath
rm applicationservice\spring.first.log 

rmdir applicationservice\.settings /S /Q
rmdir applicationservice\target /S /Q


rm interfaces\.project
rm interfaces\.classpath
rm interfaces\spring.first.log

rmdir interfaces\.settings /S /Q
rmdir interfaces\target /S /Q

rm create.bat

call mvn archetype:create-from-project
cd target\generated-sources\archetype
ren src\main\resources\archetype-resources\applicationservice\src\main\java\springcxf __parentArtifactId__
ren src\main\resources\archetype-resources\applicationservice\src\itest\java\springcxf __parentArtifactId__
ren src\main\resources\archetype-resources\applicationservice\src\test\java\springcxf __parentArtifactId__
ren src\main\resources\archetype-resources\interfaces\src\main\java\springcxf __parentArtifactId__
ren src\main\resources\archetype-resources\interfaces\src\itest\java\springcxf __parentArtifactId__
ren src\main\resources\archetype-resources\interfaces\src\test\java\springcxf __parentArtifactId__

start .
rmdir target /S /Q
findstr /S springcxf *.*