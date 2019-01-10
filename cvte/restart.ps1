docker ps | where {$_ -match "\S{12}"} | %{ $_.Split(' ')[0];} | %{&"docker" kill $_} 
docker run -p 8080:8080 -p 5001:50000 -e TASK_HOST=172.18.91.177:8090 timothy/jenkins:1.0.0