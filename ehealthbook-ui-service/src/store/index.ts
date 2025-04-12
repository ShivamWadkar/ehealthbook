import { configureStore } from '@reduxjs/toolkit';
import { thunk } from 'redux-thunk';
import logger from 'redux-logger';
import signUp from '../sign-up/redux/signUpSlice.ts';
import signIn from '../sign-in-side/redux/signInSlice.ts'

const store = configureStore({
  reducer: {
    signUp: signUp,
    signIn: signIn
  },
  // Use getDefaultMiddleware to correctly extend the default middleware
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware().concat(thunk, logger),
});

export default store;
export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
