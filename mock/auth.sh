cp auth-backup.json auth.json
json-server --host 192.168.25.104 --port 8081 --watch auth.json