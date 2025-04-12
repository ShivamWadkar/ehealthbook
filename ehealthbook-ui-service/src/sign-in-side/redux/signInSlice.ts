import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import { ApiResponse } from '../services/signInService';

export interface SignInState {
  isLoading: boolean
  error: string | null
  httpStatus: number | null | undefined
}
// Initial state
const initialState: SignInState = {
  isLoading: false,
  error: null,
  httpStatus: null
};

// Create the slice
const signInSlice = createSlice({
  name: 'signIn',
  initialState,
  reducers: {
    signInLoadingAction: (state) => {
      return {
        ...state,
        isLoading: true,
        error: null,
        httpStatus: 0
      }
    },
    signInUpdateAction: (state, action: PayloadAction<{data: ApiResponse}>) => {
      return {
        ...state,
        isLoading: false,
        error: null,
        httpStatus: action.payload.data.httpStatus
      }
    },
    signInErrorAction: (state, action: PayloadAction<{errorMessage: string, httpStatus: number | undefined}>) => {
      return {
        ...state,
        isLoading: false,
        error: action.payload.errorMessage,
        httpStatus: action.payload.httpStatus
      }
    }
  }
});

export const {
  signInLoadingAction,
  signInUpdateAction,
  signInErrorAction
} = signInSlice.actions

export default signInSlice.reducer;
