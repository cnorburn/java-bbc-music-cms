#!/bin/bash
x=1
for (( ; ; ))
do
  curl -insecure https://api.test.bbc.co.uk/music/users/
  curl -insecure https://api.test.bbc.co.uk/music/users/12a89fe1ee5
  curl -insecure https://api.test.bbc.co.uk/music/users/12a89fe1ee5/collections
  curl -insecure https://api.test.bbc.co.uk/music/users/12a89fe1ee5/playlists
  curl -insecure https://api.test.bbc.co.uk/music/playlists/12a8a1dfb15
  curl -insecure https://api.test.bbc.co.uk/music/tracks/12a8a5412ec
  curl -insecure https://api.test.bbc.co.uk/music/users/collections/12a89fe1faa
  curl -insecure https://api.test.bbc.co.uk/music/clips/p009fs8k
  x=$((x+1))
  echo "Hit $x times"
done