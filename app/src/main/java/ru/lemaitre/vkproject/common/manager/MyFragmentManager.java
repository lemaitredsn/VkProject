package ru.lemaitre.vkproject.common.manager;

import androidx.annotation.IdRes;
import androidx.fragment.app.FragmentTransaction;

import java.util.Stack;

import ru.lemaitre.vkproject.ui.activity.BaseActivity;
import ru.lemaitre.vkproject.ui.fragment.BaseFragment;

/**
 * MyFragmentManager управляет логикой
 * размещения фрагментов добавление\замена\удаление
 */


public class MyFragmentManager {
    /**
     * Константа это минимальный размер стэка
     */
    private static final int EMPTY_FRAGMENT_STACK_SIZE = 1;

    /**
     * Очередь из фрагментов
     */
    private Stack<BaseFragment> mFragmentStack = new Stack<>();
    /**
     * Текущий фрагмент
     */
    private BaseFragment mCurrentFragment;


    /**
     * Проверка существует ли activity и не завершается ли
     * после этого создаем транзакцию и заменяем  фрагмент в контейнере
     * не добавляя в бэкстэк
     * @param activity
     * @param fragment
     * @param containerId
     */
    public void setFragment(BaseActivity activity, BaseFragment fragment, @IdRes int containerId){
        if( activity != null && !activity.isFinishing() && !isAlreadyContains(fragment)){
            FragmentTransaction transaction = createAddTransaction(activity, fragment, false);
            transaction.replace(containerId, fragment);
            commitAddTransaction(activity,fragment, transaction, false);
        }
    }

    /**
     * То же ято и выше, но добавляем в контейнер фрагмент и добавляем в бэкстек
     * @param activity
     * @param fragment
     * @param containerId
     */
    public void addFragment(BaseActivity activity, BaseFragment fragment, @IdRes int containerId){
        if(activity != null && !activity.isFinishing() &&  !isAlreadyContains(fragment)){
            FragmentTransaction transaction = createAddTransaction(activity, fragment, true);
            transaction.add(containerId, fragment);
            commitAddTransaction(activity, fragment, transaction, true);
        }
    }

    /**
     * Создаем транзакцию фрагментов
     * добавляем в бэкстек если нужно
     *
     * @param activity
     * @param fragment
     * @param addToBackStack
     * @return
     */
    private FragmentTransaction createAddTransaction(BaseActivity activity,
                                                     BaseFragment fragment,
                                                     boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager()
                .beginTransaction();

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getTag());
        }
        return fragmentTransaction;
    }

    /**
     * Делаем commit транзакции, добавляем в стек фрагментов если нужно,
     * устаналиваем текущим фрагментов пришедший
     * @param activity
     * @param fragment
     * @param transaction
     * @param addToBackStack
     */
    private void commitAddTransaction(BaseActivity activity,
                                      BaseFragment fragment,
                                      FragmentTransaction transaction,
                                      boolean addToBackStack) {
        if(transaction != null){
            mCurrentFragment = fragment;

            if(!addToBackStack){
                mFragmentStack = new Stack<>();
            }

            mFragmentStack.add(fragment);

            commitTransaction(activity, transaction);
        }
    }

    /**
     * Удаляем фрагменты
     * @param activity
     */

    public boolean removeCurrentFragment(BaseActivity activity){
        return  removeFragment(activity, mCurrentFragment);
    }

    public boolean removeFragment(BaseActivity activity, BaseFragment fragment){
        boolean canRemove = fragment != null && mFragmentStack.size() > EMPTY_FRAGMENT_STACK_SIZE;

        if(canRemove){
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

            mFragmentStack.pop();
            mCurrentFragment = mFragmentStack.lastElement();

            transaction.remove(fragment);
            commitTransaction(activity, transaction);
        }
        return canRemove;
    }

    /**
     * Выполняем коммит транзакции и устанавливаем текущий фрашмент в активити
     * @param activity
     * @param transaction
     */
    private void commitTransaction(BaseActivity activity, FragmentTransaction transaction){
        transaction.commit();

        activity.fragmentOnScreen(mCurrentFragment);
    }

    /**
     * проверяем фрагмент который пришел не тот же самый что и текущий
     * @param baseFragment
     * @return
     */
    public boolean isAlreadyContains(BaseFragment baseFragment){
        if(baseFragment == null){
            return false;
        }
        return mCurrentFragment != null && mCurrentFragment.getClass().getName().equals(baseFragment.getClass().getName());
    }
}
