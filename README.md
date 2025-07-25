To run tests use:
`./gradlew test`

To run the application use:
	`./gradlew bootRun`

There is `populate.sh` script in the root folder that creates bunch of cats and dogs.

Returns all pets:
	`curl localhost:8080/pets`

Retuns pets by ownerId
	`curl localhost:8080/pets?ownerId=2`

Out of zone query:
	`curl localhost:8080/pets/outofzone`
