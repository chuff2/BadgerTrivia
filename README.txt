Conner Huff
chuff2@wisc.edu
https://youtu.be/qPK9Ms16MeE

Some details about my app... I have to main activities, first I have WelcomeActivity which is 
simply the welcome screen where the user can press start to begin the quiz. Second I have 
PlayActivity which acts as the container activity for the question fragments. This activity
is launched when the user presses start. Once that new activity begins it pulls in the first
question, which are modeled as objects from the Question class that I created. Based on 
whichever type of question we are currently looking at, either an ImageBasedFragment or a 
TextBasedFragment will be initialized and inflated. Each respective fragment takes up the entire
screen and allows the user to edit an EditText or RadioButtons, respectively. When the user answers
a question, a callback is utilized to communicate whether they got the question right or wrong 
with the main PlayActivity, which is keeping score in the game. Finally, when the game ends
the score is displayed and the user is given an option to quit and return to the main screen 
or they can replay which will bring them back to the first question with a blank score slate. 
