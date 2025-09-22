@echo off
REM Hospital Management System - Docker Setup Script for Windows
REM This script provides an easy way to build and run the application

echo 🏥 Hospital Management System - Docker Setup
echo ==============================================

if "%1"=="build" goto build
if "%1"=="start" goto start
if "%1"=="stop" goto stop
if "%1"=="restart" goto restart
if "%1"=="logs" goto logs
if "%1"=="cleanup" goto cleanup
if "%1"=="run" goto run
goto usage

:check_docker
docker info >nul 2>&1
if errorlevel 1 (
    echo ❌ Docker is not running. Please start Docker and try again.
    exit /b 1
)
echo ✅ Docker is running
goto :eof

:build
call :check_docker
echo 🔨 Building Hospital Management application...
docker-compose build --no-cache
if errorlevel 1 (
    echo ❌ Build failed
    exit /b 1
)
echo ✅ Build completed successfully
goto end

:start
call :check_docker
echo 🚀 Starting Hospital Management application...
docker-compose up -d
if errorlevel 1 (
    echo ❌ Failed to start application
    exit /b 1
)
echo ✅ Application started successfully
echo.
echo 🌐 Access points:
echo    - Main API: http://localhost:8085/patients
echo    - H2 Console: http://localhost:8085/h2-console
echo    - Health Check: http://localhost:8085/actuator/health
echo.
echo 📊 To view logs: docker-setup.bat logs
echo 🛑 To stop: docker-setup.bat stop
goto end

:stop
call :check_docker
echo 🛑 Stopping Hospital Management application...
docker-compose down
echo ✅ Application stopped successfully
goto end

:restart
call :check_docker
echo 🔄 Restarting Hospital Management application...
docker-compose restart
echo ✅ Application restarted successfully
goto end

:logs
call :check_docker
echo 📊 Showing application logs...
docker-compose logs -f
goto end

:cleanup
call :check_docker
echo 🧹 Cleaning up Docker resources...
docker-compose down -v --remove-orphans
docker system prune -f
echo ✅ Cleanup completed
goto end

:run
call :check_docker
call :build
if not errorlevel 1 call :start
goto end

:usage
echo Usage: docker-setup.bat {build^|start^|stop^|restart^|logs^|cleanup^|run}
echo.
echo Commands:
echo   build    - Build the Docker image
echo   start    - Start the application
echo   stop     - Stop the application
echo   restart  - Restart the application
echo   logs     - Show application logs
echo   cleanup  - Clean up Docker resources
echo   run      - Build and start (one command setup)
echo.
echo Quick start: docker-setup.bat run
exit /b 1

:end