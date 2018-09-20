import sys
import os

from PySide2.QtGui import QGuiApplication
from PySide2.QtQml import QQmlApplicationEngine

from view_model import ViewModel

if __name__ == '__main__':
    myApp = QGuiApplication(sys.argv)
    engine = QQmlApplicationEngine()
    context = engine.rootContext()

    # import the path for the Qt Application Manager
    #engine.addImportPath("/home/bob/Qt/5.11.2/Automotive/sources/qtapplicationmanager/dummyimports/")

    # create a view model
    view_model = ViewModel()

    # bind the view model to the context
    context.setContextProperty('view_model', view_model)

    # load the main QML window
    engine.load('mainwindow.qml')
    if not engine.rootObjects():
        sys.exit(-1)

    sys.exit(myApp.exec_())