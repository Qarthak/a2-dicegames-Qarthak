# Dice Games

A simple game app for a mobile app development course. Students will be able to demonstrate their understanding of:

* Activity lifecycle
* Intents
* Persisting the UI state
* Developing UI for landscape and portrait mode

# Assignment 2: Dice Games App

A)
Sarthak Chaudhary
2019A7PS0125G
f20190125@goa.bits-pilani.ac.in

B)
The app has one main activity where one can roll a die to earn some coin and can go to another activity where they can gamble their hard earned money.
Known Bugs:
i) The TwoOrMoreActivity view model uses a constraint layout and while it appears well on the xml preview, it breaks on the emulator.
ii) The code assumes 4 dies are already created and hence fails the "Not enough dies to play 4 alike" testcase. I tried changing it but too many things depend on those dies being initialized

C)

1) I implemented this task when I watched the lectures the first time. It follows the lectures very closely except I initialize the die to 0 at start and update the UI before the button is even pressed. Because of this, the button shows 0 because of the code instead of the xml file
2) The dies are modelled using a array. The play function uses a hashmap to find the frequencies of each of the 6 numbers and checks the max frequency against the game type. This way it can scale well for increased number of dies.
3) I connected my app and found that the landscape UI is broken for both activities. This are a few lines of commented code for radioButton that didn't work.
4) The back button worked based on a decprecated method because I followed the slides. I followed the slides closely for this
5) I had a lot of trouble with the info activity as I accidentaly created a basic one and not an empty one. I had to delete each connected part of that activity and create an empty one. It was easy to link after that
6) I developed UI for landscape for both. The TwoOrMoreActivity view is broken, at least for my emulator.
7) It was difficult to navigate the app after closing eyes and using talk back. The button size can be improved upon, the dice in the two or more game could be made smaller to make room for the buttons.

D)
I did follow a test based approach but unlike last time my tests were based on implementing functions like the back button and orientation change instead of checking the logic. My app crashed too many times to count, mostly because I was acccesing a null object. I didn't use the monkey tool

E) Time to complete: 18 hours

F) Difficulty : 9/10 lots of new stuff
