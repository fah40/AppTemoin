@echo off
setlocal

@rem 
set "TOMCAT_PATH=C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps"

@rem Get the principal directory name
for %%I in ("%CD%") do set DIRECTORY=%%~nI

if exist "temp" (
    rmdir /s /q .\temp
)

@rem creation du dossier temporaire
mkdir .\temp
mkdir .\temp\WEB-INF
mkdir .\temp\WEB-INF\classes
mkdir .\temp\WEB-INF\lib
mkdir .\java

@rem Copy des config
copy .\config .\temp\WEB-INF

@rem Copy lib to temp
copy .\lib .\temp\WEB-INF\lib

@rem Copy lib to temp
copy .\web .\temp

@rem Définir les chemins source et destination
set "source_folder=.\src"
set "destination_folder=.\java"

@rem Boucle sur les sous-dossiers pour copier les fichiers .java
for /D %%d in ("%source_folder%\*") do (
    for %%f in ("%%d\*.java") do (
        copy "%%f" "%destination_folder%"
    )
)

@rem Compiler les fichiers .java dans "classes"
javac -parameters -d .\temp\WEB-INF\classes -cp "lib\*" .\java\*.java

@rem Convert temp into .war and move to Tomcat server
cd temp
jar -cvf "%DIRECTORY%.war" *
copy "%DIRECTORY%.war" "%TOMCAT_PATH%"

pause