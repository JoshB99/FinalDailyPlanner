# Design Decisions

The MainActivity of our app presents a listview of the daily tasks. In order to allow checkboxes and within the list, we created a Goal
class and Custom Adapter. We also added a floating action button for adding new tasks as well as a drawer layout for navigation between 
tasks and long-term goals. We also implemented an onclicklistener for the listview. This allows us to click on an individual list item and
edit or delete it.

In the AddTask activity, we allow users to input a new task. We also allow users to link their task to long-term goals. The idea is that
their short-term tasks can be working towards larger goals. This is done by accessing the goal table of our databases and inputting these
values as options in the spinner. These tasks are stored in a database.

In the LongTermGoals activity, we display any long-term goals the user might have. There is again access to the navigation drawer as well
as a floating action button for adding new goals. We can also click on a list item and edit or delete it. This activity also accepts the
current parameters saved in the database and inputs them into the different views. After editting or deleting, the user is redirected back
to the previous page.

In the the AddGoal activity, users can input a new long-term goal. The user must also input a date, description, and type. These are all
stored in a database. 

In the EditTask activity, users edit or delete individual tasks. If updated, the app will search through the database for the item id and
update all items with new items. If deleted, it will search database for id, and delete that row.

In the EditGoal activity, users can edit or delete goals. If updated, the app will search through the database for the item id and 
update all items with new items. If deleted, it will search database for id, and delete that row.

The Database Helper is a java class that helps us incorperate the power of SQLite capabilities into our application. 
Through this we have the ability to create tables that will be able to store all of our data including tasks and goals. We created two
tables, one for long-term goals and one for tasks. We created functions that return, edit, and delete different parts of the table based
on passed in parameters.
