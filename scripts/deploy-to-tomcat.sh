#!/usr/bin/env bash
set -euo pipefail

WAR_FILE="${1:-target/naturehoney.war}"
TOMCAT_WEBAPPS="${TOMCAT_WEBAPPS:-/opt/tomcat/webapps}"

if [[ ! -f "$WAR_FILE" ]]; then
  echo "WAR not found: $WAR_FILE" >&2
  exit 1
fi

if [[ ! -d "$TOMCAT_WEBAPPS" ]]; then
  echo "Tomcat webapps directory not found: $TOMCAT_WEBAPPS" >&2
  exit 1
fi

cp "$WAR_FILE" "$TOMCAT_WEBAPPS/naturehoney.war"
echo "Copied $(basename "$WAR_FILE") to $TOMCAT_WEBAPPS"
echo "Restart Tomcat using your environment's approved startup method."
