#!/usr/bin/env bash
# Déploie une image sur la plateforme : deploy/deployer.sh <image> [image-précédente]
# (écrit vite, un vendredi soir)
set -uo pipefail
cd "$(dirname "$0")"
./plateforme.sh lancer "$1"
sleep 5
echo "Déployé !"
