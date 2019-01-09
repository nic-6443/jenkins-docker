docker ps | where {$_ -match "\S{12}"} | %{ $_.Split(' ')[0];} | %{&"docker" kill $_} 
docker run -p 8080:8080 -p 50000:50000 timothy/jenkins:1.0.0