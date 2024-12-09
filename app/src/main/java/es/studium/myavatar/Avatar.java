package es.studium.myavatar;

public class Avatar {
    private final String name;
    private final String gender;
    private final String species;
    private final String profession;
    private final int life;
    private final int magic;
    private final int strength;
    private final int speed;

    public Avatar(String name, String gender, String species, String profession, int life, int magic, int strength, int speed) {
        this.name = name;
        this.gender = gender;
        this.species = species;
        this.profession = profession;
        this.life = life;
        this.magic = magic;
        this.strength = strength;
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Nombre: " + name +
                "\nGénero: " + gender +
                "\nEspecie: " + species +
                "\nProfesión: " + profession +
                "\nVida: " + life +
                "\nMagia: " + magic +
                "\nFuerza: " + strength +
                "\nVelocidad: " + speed;
    }
}