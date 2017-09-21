import sys
from PyQt5.QtWidgets import QApplication, QPushButton, QMessageBox, QDesktopWidget, \
    QToolTip, QMainWindow, QAction, QMenu
from PyQt5.QtCore import pyqtSlot
from PyQt5.QtGui import QIcon, QFont


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

        alert_button = QPushButton('Show Alert', self)
        alert_button.setToolTip('this is a <b>tooltip</b>')
        alert_button.resize(alert_button.sizeHint())    # not sure what this does or if necessary
        alert_button.move(100, 70)
        alert_button.clicked.connect(self.alert_button__on_click)

        self.show()

    def center(self):
        qr = self.frameGeometry()
        dw = QDesktopWidget()
        cp = dw.availableGeometry().center()
        qr.moveCenter(cp)
        self.move(qr.topLeft())

    def closeEvent(self, event):
        reply = QMessageBox.question(self, "Message", "Are you sure you want to quit?",
                                     QMessageBox.Yes | QMessageBox.No, QMessageBox.No)

        if reply == QMessageBox.Yes:
            event.accept()
        else:
            event.ignore()

    @pyqtSlot()
    def alert_button__on_click(self):
        print('button clicked')
        QMessageBox.about(self, "Alert", "Hello World!!!")


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = App()
    sys.exit(app.exec_())
