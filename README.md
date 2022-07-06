# Github-User
Trying load github user api and show in the list using recyclerview.

This project using MVP (Mocel-View-Presenter) Pattern
Support for offline mode (Room Database) and add some logic in adapter every fourth avatar colour should have its image colours inverted.
Maybe got some lag because there are methode that invert image in GithubUserAdapter.java, to remove that lag just comment invertImage methode in GithubUserAdapter.java file.

items in users list are greyed out a bit for seen profile.

this app has support dark mode, and you can change that theme in the toolbar.

list using lazy loading.
