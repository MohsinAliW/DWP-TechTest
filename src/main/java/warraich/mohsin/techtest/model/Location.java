package warraich.mohsin.techtest.model;

public enum Location {
	
	LONDON("London", new Coordinates(51.50853, -0.12574));

    private final String name;
    private final Coordinates coordinates;

    Location(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

}
