@ECHO ON
@NO HACER UPDATE DE ESTE FICHERO EN SVN

SET DEPDIR=*****RUTA HASTA EL SERVIDOR DE WEBLOGIC*****\user_projects\domains\base_domain\autodeploy
SET EAR=gesaduan-ear-0.0.1

rmdir %DEPDIR%\%EAR%.ear

IF "%1"=="build" call mvn -U clean package

mklink /J %DEPDIR%\%EAR%.ear target\%EAR%