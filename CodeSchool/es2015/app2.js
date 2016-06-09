import { alertMessage, logMessage } from './flash-message2';

alertMessage("hello alert");
logMessage("hello log");


//another way

import * as flash from './flash-message2';

flash.alertMessage("hello alert");
flash.logMessage("hello log");
