#!/bin/bash
set -e

psql -U postgres -d Library_DB -f /docker-entrypoint-initdb.d/backup.sql
