Android Viper Architecture sample
=================================

This repo provides an end to end Android app encoded using **Viper Architecture**, usual in iOS development.
Therefore, VIPER is an application of Clean Architecture to iOS apps. So the other way around, this repo is an
implementation of Clean Architecture.

The repo is a sample project for the [caster.io Android Viper Architecture course](https://caster.io) course about the same subject.

## VIPER Meaning
VIPER stands for: View, Interactor, Presenter, Entity and Router, and it's a layered architecture.
* View -> View implementation (Activities, fragments, custom views).
* Interactor -> Business logic (Use Cases)
* Presenter -> Presentation logic
* Entity -> Part of the domain, represents the model entities used by the Interactors.
* Router -> This is just a plain navigator. Routes from one activity to another and presenter talks to it.

