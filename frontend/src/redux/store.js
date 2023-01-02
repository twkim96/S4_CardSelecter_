import {configureStore} from "@reduxjs/toolkit";
import userReducer from './user';
import selectReducer from './select';

export default configureStore({
        reducer: {
            user: userReducer,
            selectCard: selectReducer,
        },
    }
)