copy auth-backup.json auth.json
json-server --host 10.22.2.109 --port 8081 --watch auth.json