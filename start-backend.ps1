$env:JAVA_HOME = "C:\Users\Shubham\java-tools\jdk21"
$env:Path = "$env:JAVA_HOME\bin;C:\Users\Shubham\java-tools\maven\bin;$env:Path"

Write-Host "=================================================" -ForegroundColor Yellow
Write-Host "   Starting Ghar Tak Spring Boot Backend...     " -ForegroundColor Yellow
Write-Host "   Tagline: A to Z Solution in One Tap          " -ForegroundColor Yellow
Write-Host "   JAVA_HOME: $env:JAVA_HOME                   " -ForegroundColor Cyan
Write-Host "=================================================" -ForegroundColor Yellow

Set-Location -Path $PSScriptRoot
mvn clean compile spring-boot:run
