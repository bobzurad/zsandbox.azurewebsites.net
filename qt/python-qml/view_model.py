"""view model"""

from PyQt5.QtCore import QObject, pyqtProperty, pyqtSignal, pyqtSlot, QVariant

class ViewModel(QObject):
    """view model"""

    _current_value = None

    propertyChanged = pyqtSignal()

    def __init__(self):
        super().__init__()

        self._current_value = 0

    @pyqtProperty('QString', notify=propertyChanged)
    def current_value(self):
        """returns current_value"""
        return str(self._current_value)
    @current_value.setter
    def current_value(self, value):
        self._current_value = value
        self.propertyChanged.emit()

    @pyqtSlot()
    def increment(self):
        """increments the current value"""
        self._current_value += 1    # set the private member to avoid overwriting current_value.setter
        self.propertyChanged.emit()

    @pyqtSlot()
    def decrement(self):
        """decrements the current value"""
        self._current_value -= 1
        self.propertyChanged.emit()