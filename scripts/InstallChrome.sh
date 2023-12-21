#!/bin/bash
set -ex

CHROME_VERSION="114.0.5735.16"


wget https://dl.google.com/linux/direct/google-chrome-stable_${CHROME_VERSION}_amd64.deb
sudo apt install ./google-chrome-stable_${CHROME_VERSION}_amd64.deb
