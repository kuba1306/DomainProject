call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openbrowser
echo.
echo Runcrud error
goto fail

:openbrowser
start chrome http://localhost:8080/crud/v1/tasks
goto end

:fail
echo. echo There were errors

:end
echo.
echo Work has finished.