#!/bin/bash

curl localhost:8080/pets -H "Content-Type: application/json" --data '{"petType": "DOG", "ownerId": 1, "trackerType": "BIG", "inZone": true}'
curl localhost:8080/pets -H "Content-Type: application/json" --data '{"petType": "DOG", "ownerId": 1, "trackerType": "MEDIUM", "inZone": true}'
curl localhost:8080/pets -H "Content-Type: application/json" --data '{"petType": "DOG", "ownerId": 2, "trackerType": "MEDIUM", "inZone": true}'
curl localhost:8080/pets -H "Content-Type: application/json" --data '{"petType": "DOG", "ownerId": 5, "trackerType": "BIG", "inZone": false}'
curl localhost:8080/pets -H "Content-Type: application/json" --data '{"petType": "DOG", "ownerId": 6, "trackerType": "SMALL", "inZone": false}'

curl localhost:8080/pets -H "Content-Type: application/json" --data '{"petType": "CAT", "ownerId": 2, "trackerType": "BIG", "inZone": true, "lostTracker": false}'
curl localhost:8080/pets -H "Content-Type: application/json" --data '{"petType": "CAT", "ownerId": 2, "trackerType": "SMALL", "inZone": false, "lostTracker": false}'
curl localhost:8080/pets -H "Content-Type: application/json" --data '{"petType": "CAT", "ownerId": 3, "trackerType": "SMALL", "inZone": false, "lostTracker": true}'
curl localhost:8080/pets -H "Content-Type: application/json" --data '{"petType": "CAT", "ownerId": 4, "trackerType": "SMALL", "inZone": false, "lostTracker": true}'
curl localhost:8080/pets -H "Content-Type: application/json" --data '{"petType": "CAT", "ownerId": 4, "trackerType": "BIG", "inZone": false, "lostTracker": false}'
