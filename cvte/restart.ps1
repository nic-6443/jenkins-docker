docker ps | where {$_ -match "\S{12}"} | %{ $_.Split(' ')[0];} | %{&"docker" kill $_} 
docker run -p 9080:8080 -p 5001:50000 -e TASK_HOST=task-dev.gz.cvte.cn:8080 timothy/jenkins:1.0.0