import {RemoteApp} from '@eclipse-scout/core';
import * as contacts from './index';

Object.assign({}, contacts); // Use import so that it is not marked as unused

new RemoteApp().init();
