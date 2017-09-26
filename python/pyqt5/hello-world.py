import sys
from PyQt5.QtWidgets import QApplication, QPushButton, QMessageBox, QDesktopWidget, \
    QToolTip, QMainWindow, QAction, QMenu, QGridLayout, QLabel
from PyQt5.QtCore import pyqtSlot, pyqtSignal, Qt, QObject
from PyQt5.QtGui import QIcon, QFont


class CloseAppSignal(QObject):
    closeApp = pyqtSignal()


class App(QMainWindow):
    def __init__(self):
        super().__init__()
        self.title = 'Hello World'
        # set position of the window
        # self.left = 100
        # self.top = 100
        # self.width = 800
        # self.height = 600
        # the above 4 lines could also be done with self.setGeometry(100, 100, 800, 600)
        self.closeAppSignal = CloseAppSignal()
        self.closeAppSignal.closeApp.connect(self.close)
        self.initUI()

    def initUI(self):
        QToolTip.setFont(QFont('SanSerif', 12))

        self.setWindowTitle(self.title)
        self.resize(800, 600)
        self.center()
        self.setWindowIcon(QIcon('doomavatar.jpeg'))
        self.statusBar().showMessage("Ready")

        # exit action
        exit_action = QAction(QIcon("doomavatar.jpeg"), "&Exit", self)
        exit_action.setShortcut("Ctrl+Q")
        exit_action.setStatusTip("Exit application")
        exit_action.triggered.connect(super().close)

        # menu and sub menu
        import_menu = QMenu("Import", self)
        import_account_action = QAction("Import Account", self)
        import_menu.addAction(import_account_action)

        # new action
        new_action = QAction("New", self)

        # menu bar
        menu_bar = self.menuBar()

        # file menu
        file_menu = menu_bar.addMenu("&File")
        file_menu.addAction(new_action)
        file_menu.addMenu(import_menu)
        file_menu.addAction(exit_action)

        # alert button
        alert_button = QPushButton('Show Alert', self)
        alert_button.setToolTip('this is a <b>tooltip</b>')
        alert_button.resize(alert_button.sizeHint())    # not sure what this does or if necessary
        alert_button.move(50, 50)
        alert_button.clicked.connect(self.alert_button__on_click)

        # track x & y mouse coordinates
        grid = QGridLayout()
        grid.setSpacing(10)
        x = 0
        y = 0
        self.xytext = "x: {0}, y: {1}".format(x, y)
        self.xylabel = QLabel(self.xytext, self)
        grid.addWidget(self.xylabel, 0, 0, Qt.AlignTop)
        self.setMouseTracking(True)
        self.setLayout(grid)

        # two buttons, same handler
        btn1 = QPushButton("Button 1", self)
        btn1.move(50, 100)
        btn2 = QPushButton("Button 2", self)
        btn2.move(50, 150)
        btn1.clicked.connect(self.button__on_click)
        btn2.clicked.connect(self.button__on_click)

        self.show()

    def center(self):
        qr = self.frameGeometry()
        dw = QDesktopWidget()
        cp = dw.availableGeometry().center()
        qr.moveCenter(cp)
        self.move(qr.topLeft())

    # override event
    def closeEvent(self, event):
        reply = QMessageBox.question(self, "Message", "Are you sure you want to quit?",
                                     QMessageBox.Yes | QMessageBox.No, QMessageBox.No)

        if reply == QMessageBox.Yes:
            event.accept()
        else:
            event.ignore()

    # override event
    def keyPressEvent(self, event):
        if event.key() == Qt.Key_Escape:
            # don't have to emit the closeAppSignal, can do this instead: self.close()
            self.closeAppSignal.closeApp.emit()

    # override event
    def mouseMoveEvent(self, event):
        x = event.x()
        y = event.y()
        text = "x: {0}, y: {1}".format(x, y)
        self.xylabel.setText(text)

    @pyqtSlot()
    def button__on_click(self):
        sender = self.sender()
        self.statusBar().showMessage(sender.text() + " was pressed")

    @pyqtSlot()
    def alert_button__on_click(self):
        print('button clicked')
        self.statusBar().showMessage(self.sender().text() + " was pressed")
        QMessageBox.about(self, "Alert", "Hello World!!!")


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = App()
    sys.exit(app.exec_())
