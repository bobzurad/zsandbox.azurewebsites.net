Goals:
 - Use the Qt Application Manager to run 2 qml apps that use python models


Prerequisites: 
 - Python 3.4 or greater
 - [Qt Application Manager](https://doc.qt.io/QtApplicationManager/index.html) (requires a Qt Automotive Suite license)


To ensure dependencies for the python apps are installed, run `"pip install ."` in each of the app folders (where setup.py is for each app)

To run from Qt Application Manager:
 - appman -c am-config.yaml -r

To run the python apps separately (without appman)
 - cd apps/app1
 - python app1.py
 - cd apps/app2
 - python app2.py
