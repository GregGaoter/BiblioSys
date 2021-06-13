import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { defaultValue, ICollection } from "../../model/CollectionModel";
import type { AppThunk, RootState } from "../store";

interface CollectionState {
  entity: ICollection;
  entities: ReadonlyArray<ICollection>;
  loading: boolean;
  updating: boolean;
  updateSuccess: boolean;
  errorMessage: string | undefined;
}

const initialState: Readonly<CollectionState> = {
  entity: defaultValue,
  entities: [],
  loading: false,
  updating: false,
  updateSuccess: false,
  errorMessage: undefined,
};

// Reducer

const collectionSlice = createSlice({
  name: "collection",
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
      state.entity = defaultValue;
      state.updating = false;
      state.updateSuccess = true;
    },
    resetState: (state) => {
      state.entity = defaultValue;
      state.entities = [];
      state.loading = false;
      state.updating = false;
      state.updateSuccess = false;
      state.errorMessage = undefined;
    },
  },
});

export const baseUrl = "/collection";

const {
  requestGetEntityEntities,
  requestCreateUpdateDeleteEntity,
  failure,
  successGetEntity,
  successGetEntities,
  successCreateUpdateEntity,
  successDeleteEntity,
  resetState,
} = collectionSlice.actions;

// Actions

export const getEntity =
  (id: number): AppThunk =>
  async (dispatch: any) => {
    dispatch(requestGetEntityEntities);
    try {
      await axios.get<ICollection>(`${baseUrl}/${id}`).then((response) => dispatch(successGetEntity(response)));
    } catch (e) {
      dispatch(failure(e.message));
    }
  };

export const getEntities = (): AppThunk => async (dispatch: any) => {
  dispatch(requestGetEntityEntities);
  try {
    await axios.get<ICollection>(`${baseUrl}/all`).then((response) => dispatch(successGetEntities(response)));
  } catch (e) {
    dispatch(failure(e.message));
  }
};

export const createEntity =
  (entity: ICollection): AppThunk =>
  async (dispatch: any) => {
    dispatch(requestCreateUpdateDeleteEntity);
    try {
      const response = await axios.post<ICollection>(`${baseUrl}`, entity);
      dispatch(successCreateUpdateEntity(response));
      await dispatch(getEntities());
      return response;
    } catch (e) {
      dispatch(failure(e.message));
    }
  };

export const updateEntity =
  (id: number, entity: ICollection): AppThunk =>
  async (dispatch: any) => {
    dispatch(requestCreateUpdateDeleteEntity);
    try {
      await axios
        .put<ICollection>(`${baseUrl}/${id}`, entity)
        .then((response) => dispatch(successCreateUpdateEntity(response)));
    } catch (e) {
      dispatch(failure(e.message));
    }
  };

export const deleteEntity =
  (id: number): AppThunk =>
  async (dispatch: any) => {
    dispatch(requestCreateUpdateDeleteEntity);
    try {
      const response = await axios.delete<ICollection>(`${baseUrl}`);
      dispatch(successDeleteEntity);
      await dispatch(getEntities());
      return response;
    } catch (e) {
      dispatch(failure(e.message));
    }
  };

export const reset = () => (dispatch: any) => {
  dispatch(resetState);
};

// Selectors

export const entity = (state: RootState) => state.collection.entity;
export const entities = (state: RootState) => state.collection.entities;
export const loading = (state: RootState) => state.collection.loading;
export const updating = (state: RootState) => state.collection.updating;
export const updateSuccess = (state: RootState) => state.collection.updateSuccess;
export const errorMessage = (state: RootState) => state.collection.errorMessage;

export default collectionSlice.reducer;
