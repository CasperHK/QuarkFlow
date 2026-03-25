@echo off
chcp 65001 >nul
echo ========================================
echo   QuarkFlow - Install Dependencies
echo ========================================
echo.

cd /d "%~dp0my-app"

echo [1/2] Installing frontend dependencies (pnpm)...
echo.
cd src\main\resources\web
call pnpm install
if %errorlevel% neq 0 (
    echo.
    echo ERROR: Frontend dependency installation failed.
    pause
    exit /b 1
)

echo.
echo [2/2] Installing backend dependencies (Maven)...
echo.
cd /d "%~dp0my-app"
call mvnw.cmd dependency:resolve -q
if %errorlevel% neq 0 (
    echo.
    echo ERROR: Backend dependency installation failed.
    pause
    exit /b 1
)

echo.
echo ========================================
echo   All dependencies installed!
echo ========================================
pause
