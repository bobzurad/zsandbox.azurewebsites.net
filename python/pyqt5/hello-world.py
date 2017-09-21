import sys
from PyQt5.QtWidgets import QApplication, QWidget, QPushButton, QMessageBox, QDesktopWidget, \
    QToolTip, QMainWindow
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

        button = QPushButton('Show Alert', self)
        button.setToolTip('this is a <b>tooltip</b>')
        button.resize(button.sizeHint())    # not sure what this does or if necessary
        button.move(100, 70)
        button.clicked.connect(self.on_click)

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
    def on_click(self):
        print('button clicked')
        QMessageBox.about(self, "Alert", "Hello World!!!")


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = App()
    sys.exit(app.exec_())
