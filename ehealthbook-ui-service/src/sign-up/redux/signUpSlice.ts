import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import { ApiResponse } from '../services/signUpService';

export interface SignUpState {
  isLoading: boolean
  error: string | null
  httpStatus: number | null | undefined
}
// Initial state
const initialState: SignUpState = {
  isLoading: true,
  error: null,
  httpStatus: null
};

// Create the slice
const signUpSlice = createSlice({
  name: 'signUp',
  initialState,
  reducers: {
    signUpLoadingAction: (state) => {
      return {
        ...state,
        isLoading: true,
        error: null,
        httpStatus: 0
      }
    },
    signUpUpdateAction: (state, action: PayloadAction<{data: ApiResponse}>) => {
      return {
        ...state,
        isLoading: false,
        error: null,
        httpStatus: action.payload.data.http_status
      }
    },
    signUpErrorAction: (state, action: PayloadAction<{errorMessage: string, httpStatus: number | undefined}>) => {
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
  signUpLoadingAction,
  signUpUpdateAction,
  signUpErrorAction
} = signUpSlice.actions

export default signUpSlice.reducer;
