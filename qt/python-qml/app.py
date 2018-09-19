import sys
import os

from PyQt5.QtWidgets import QApplication
from PyQt5.QtCore import QUrl
from PyQt5.QtQml import QQmlComponent, QQmlApplicationEngine
from PyQt5.QtQuick import QQuickWindow

from view_model import ViewModel

if __name__ == '__main__':
    myApp = QApplication(sys.argv)

    engine = QQmlApplicationEngine()
    context = engine.rootContext()
    engine.addImportPath("/home/bob/Baxter/git/bsb-core/")
    engine.addImportPath("/home/bob/Qt/5.11.2/Automotive/sources/qtapplicationmanager/dummyimports/")

    # create a view model
    view_model = ViewModel()

    # bind the view model to the context
    context.setContextProperty('view_model', view_model)

    component = QQmlComponent(engine)
    component.loadUrl(QUrl('mainwindow.qml'))

    # some boilerplate to make sure the component is ready before showing
    if component.status() != QQmlComponent.Ready:
        if component.status() == QQmlComponent.Error:
            sys.exit(component.errorString())

    root_window: QQuickWindow = component.create()

    myApp.exec_()
    sys.exit()
