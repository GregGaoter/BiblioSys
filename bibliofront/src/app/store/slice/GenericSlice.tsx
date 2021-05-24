import { createSlice, Draft, SliceCaseReducers, ValidateSliceCaseReducers } from "@reduxjs/toolkit";

interface GenericState<T> {
  entity: T;
  entities: ReadonlyArray<T>;
  loading: boolean;
  updating: boolean;
  updateSuccess: boolean;
  errorMessage: string | undefined;
}

const initialState: Readonly<GenericState<{}>> = {
  entity: {},
  entities: [],
  loading: false,
  updating: false,
  updateSuccess: false,
  errorMessage: undefined,
};

export const createGenericSlice = <T, Reducers extends SliceCaseReducers<GenericState<T>>>({
  name = "",
  initialState,
  reducers,
}: {
  name: string;
  initialState: GenericState<T>;
  reducers: ValidateSliceCaseReducers<GenericState<T>, Reducers>;
}) => {
  return createSlice({
    name,
    initialState,
    reducers: {
      requestGetEntityEntities: (state) => {
        state.loading = true;
        state.updateSuccess = false;
        state.errorMessage = undefined;
      },
      requestCreateUpdateDeleteEntity: (state) => {
        state.updating = true;
        state.updateSuccess = false;
        state.errorMessage = undefined;
      },
      failure: (state, action) => {
        state.loading = false;
        state.updating = false;
        state.updateSuccess = false;
        state.errorMessage = action.payload;
      },
      successGetEntity: (state, action) => {
        state.entity = action.payload.data;
        state.loading = false;
      },
      successGetEntities: (state, action) => {
        state.entities = action.payload.data;
        state.loading = false;
      },
      successCreateUpdateEntity: (state, action) => {
        state.entity = action.payload.data;
        state.updating = false;
        state.updateSuccess = true;
      },
      successDeleteEntity: (state) => {
        state.entity = {} as Draft<T>;
        state.updating = false;
        state.updateSuccess = true;
      },
      resetState: (state) => {
        state.entity = {} as Draft<T>;
        state.entities = [];
        state.loading = false;
        state.updating = false;
        state.updateSuccess = false;
        state.errorMessage = undefined;
      },
    },
  });
};
