@echo off
chcp 65001 >nul
echo ========================================
echo   QuarkFlow - Dev Mode
echo ========================================
echo.
echo Starting Vite dev server + Quarkus dev...
echo.

cd /d "%~dp0my-app\src\main\resources\web"
start "Vite Dev Server" cmd /c "pnpm dev"

timeout /t 2 /nobreak >nul

cd /d "%~dp0my-app"
echo Vite started on http://localhost:5173
echo Starting Quarkus on http://localhost:8080 ...
echo.
call mvnw.cmd quarkus:dev
