$env:JAVA_HOME = "C:\Users\Shubham\java-tools\jdk21"
$env:Path = "$env:JAVA_HOME\bin;C:\Users\Shubham\java-tools\maven\bin;$env:Path"

$env:SPRING_DATASOURCE_URL = "postgresql://postgres:wCRBNlJqliuFinitkZzLaaryEvwLqMTF@turntable.proxy.rlwy.net:30979/railway"

Write-Host "=================================================" -ForegroundColor Yellow
Write-Host "   Starting Ghar Tak Spring Boot Backend...     " -ForegroundColor Yellow
Write-Host "   Database: Railway PostgreSQL (turntable.proxy.rlwy.net:30979/railway)" -ForegroundColor Green
Write-Host "   Tagline: A to Z Solution in One Tap          " -ForegroundColor Yellow
Write-Host "   JAVA_HOME: $env:JAVA_HOME                   " -ForegroundColor Cyan
Write-Host "=================================================" -ForegroundColor Yellow

Set-Location -Path $PSScriptRoot
mvn clean compile spring-boot:run
